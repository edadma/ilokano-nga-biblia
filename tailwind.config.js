/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: ["./target/scala-3.3.1/ilokano-nga-biblia-fastopt/**/*.{html,js}", "./public/**/*.html"],
  theme: {
    extend: {
      fontFamily: {
        gentium: ['Gentium Plus', 'serif'],
        fondamento: ['Fondamento', 'serif'],
        quintessential: ['Quintessential', 'serif']
      },
      typography: {
        DEFAULT: {
          css: {
            maxWidth: '100%',
          },
        },
      }
    }
  },
  plugins: [
    require('@tailwindcss/typography'),
    require('@tailwindcss/forms')
  ],
}
