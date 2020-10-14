(ns app.components)

(defn container [& children]
  [:div.bg-gray-100.py-8.px-8 children])

(defn cards [& children]
  [:ul.grid.grid-cols-1.gap-6.sm:grid-cols-2.lg:grid-cols-3
     children])

(defn card [{:keys [title subtitle tag]}]
  [:li.col-span-1.bg-white.rounded-lg.shadow
   [:div.w-full.flex.items-center.justify-between.p-6.space-x-6
    [:div.flex-1
     [:div.flex.items-center.space-x-3
      [:h3.text-gray-900.text-sm.leading-5.font-medium
       title]
      [:span.flex-shrink-0.inline-block.px-2.py-0.5.text-teal-800.text-xs.leading-4.font-medium.bg-teal-100.rounded-full
       tag]]
     [:p.mt-1.text-gray-500.text-sm
      subtitle]]]
   [:div.border-t.border-gray-200
    [:div.-mt-px.flex
     [:div.w-0.flex-1.flex.border-r.border-gray-200
      [:a.relative.-mr-px.w-0.flex-1.inline-flex.items-center.justify-center.py-4.text-sm.leading-5.text-gray-700.font-medium.border.border-transparent.rounded-bl-lg.hover:text-gray-500.focus:outline-none.focus:shadow-outline-blue.focus:border-blue-300.focus:z-10.transition.ease-in-out.duration-150
       {:href "#"}
       [:span.ml-3 "Watch"]]]
     [:div.-ml-px.w-0.flex-1.flex
      [:a.relative.w-0.flex-1.inline-flex.items-center.justify-center.py-4.text-sm.leading-5.text-gray-700.font-medium.border.border-transparent.rounded-br-lg.hover:text-gray-500.focus:outline-none.focus:shadow-outline-blue.focus:border-blue-300.focus:z-10.transition.ease-in-out.duration-150
       {:href "#"}
       [:span.ml-3 "Edit"]]]]]])


(defn search-bar [{:keys [value on-change]}]
  [:div.flex-1.px-4.flex.justify-between.sm:px-6.lg:max-w-6xl.lg:mx-auto.lg:px-8.my-1
    [:div.flex-1.flex
     [:form.w-full.flex.md:ml-0
      {:method "GET", :action "#"}
      [:label.sr-only {:for "search_field"} "Search"]
      [:div.relative.w-full.text-cool-gray-400.focus-within:text-cool-gray-600
       [:div.absolute.inset-y-0.left-0.flex.items-center.pointer-events-none
        [:svg.h-5.w-5
         {:viewbox "0 0 20 20", :fill "currentColor"}
         [:path
          {:d
           "M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z",
           :clip-rule "evenodd",
           :fill-rule "evenodd"}]]]    
       [:input#search_field.block.w-full.h-full.pl-8.pr-3.py-2.rounded-md.text-cool-gray-900.placeholder-cool-gray-500.focus:outline-none.focus:placeholder-cool-gray-400.sm:text-sm
        {:type "text", :placeholder "Search" :value value :on-change #(on-change (-> % .-target .-value))}]]]]])
