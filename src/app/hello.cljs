(ns app.hello
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [cljs-http.client :as http]
              [clojure.string :as s]
              [reagent.core :as r]
              [cljs.core.async :refer [<!]]
              [app.components :refer [card cards container search-bar]]))

(def state (r/atom {:search ""
                    :videos [{:slug "java-concurrency-primitives-part-II"
                              :video-id "6e08c8b692c1f304dac4a5e437fb1d75"
                              :description "Wszystko co chciałbyś wiedzieć o Java concurrency część II."
                              :title "Java concurrency primitives część II"}
                             {:slug "java-concurrency-primitives-part-I"
                              :video-id "6e08c8b692c1f304dac4a5e437fb1d73"
                              :description "Wszystko co chciałbyś wiedzieć o Java concurrency część I."
                              :title "Java concurrency primitives część I"}
                             {:slug "alternative-concurency-models-I"
                              :video-id "6e08c8b692c1f304dac4a5e437fb1d72"
                              :description "Nie tylko thready. Alternatywe modele wspolbieznosci część I."
                              :title "Modele wspolbieznosci część I"}]}))

(defn by-title [search]
  #(s/includes? (s/lower-case (:title %)) (s/lower-case search)))

(defn update-state [key value]
  (swap! state update-in [key] (fn [_] value)))

(defn video-card [{:keys [slug video-id title description]}]
  [card {:title title :subtitle description :tag "unwatched"}])

(defn searchbox [val]
  [search-bar {:value val :on-change #(update-state :search %)}])

(defn videos-list [videos]
  (cards (for [video videos]
           ^{:key (:video-id video)} [video-card video])))

(defn hello []
  (let [{:keys [search videos]} @state]
    [:<>
     [searchbox search]
     [container
      [videos-list (->> videos (filter (by-title search)))]]]))

(go (let [response (<! (http/get "https://api.video.segfault.events/videos/"))]
      (prn (:status response))
      (prn (:body response))
      (if (= (:status response) 200)
        (update-state :videos (:body response)))))
