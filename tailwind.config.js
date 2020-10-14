module.exports = {
  purge: [
    './src/**/*.html',
    './src/**/*.cljs',
  ],
  theme: {
    extend: {}
  },
  variants: {},
  plugins: [
    require('@tailwindcss/ui'),
  ]
}
