(ns app.hello
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [cljs-http.client :as http]
              [clojure.string :as s]
              [reagent.core :as r]
              [cljs.core.async :refer [<!]]))

(go (let [response (<! (http/get "https://api.video.segfault.events/videos/"))]
      (prn (:status response))
      (prn (:body response)
           #_(update-state :videos (:body respose)))))

(def state (r/atom {:search ""
                    :videos [{:slug "java-concurrency-primitives-part-II"
                              :video-id "6e08c8b692c1f304dac4a5e437fb1d75"
                              :description "Wszystko co chciałbyś wiedzieć o Java concurrency część II."
                              :title "Java concurrency primitives część II"}
                             {:slug "java-concurrency-primitives-part-I"
                              :video-id "6e08c8b692c1f304dac4a5e437fb1d75"
                              :description "Wszystko co chciałbyś wiedzieć o Java concurrency część I."
                              :title "Java concurrency primitives część I"}]}))

(defn by-title [search]
  #(s/includes? (:title %) search))

(defn update-state [key value]
  (swap! state update-in [key] (fn [_] value)))

(defn video-row [{:keys [slug video-id title description]}]
  [:tr [:td slug] [:td title] [:td description]])

(defn searchbox [val]
  [:input {:type "text" :value val :placeholder "search phrase"
           :on-change #(update-state :search (-> % .-target .-value))}])

(defn videos-table [videos]
  [:table
   [:thead>tr [:td "slug"] [:td "title"] [:td "description"]]
   [:tbody (for [video videos]
             ^{:key (:video-id video)} [video-row video])]])

(defn hello []
  (let [{:keys [search videos]} @state]
    [:<>
     [:p "Hello, segfault is running!"]
     [searchbox search]
     [videos-table (->> videos (filter (by-title search)))]]))
