/** @type {import('tailwindcss').Config} */
export default {
    content: [
      "./index.html",
      "./src/**/*.{vue,js,ts,jsx,tsx}",
    ],
    theme: {
        screens: {
            'sm': '640px',
            // => @media (min-width: 640px) { ... }

            'md': '768px',
            // => @media (min-width: 768px) { ... }

            'lg': '1024px',
            // => @media (min-width: 1024px) { ... }

            'xl': '1280px',
            // => @media (min-width: 1280px) { ... }

            '2xl': '1536px',
            // => @media (min-width: 1536px) { ... }
        },
        fontFamily: {
            sans: ['Graphik', 'sans-serif'],
            serif: ['Merriweather', 'serif'],
        },
        
      extend: {
        spacing: {
            '1': '8px',
            '2': '12px',
            '3': '16px',
            '4': '24px',
            '5': '32px',
            '6': '48px',
            '7': '96rem',
            '8': '128rem',
        },
        colors:{
          'beige': '#F6E9B2',
          'forest-green': '#0A6847',
          'leaf-green': '#7ABA78',
          'sand': '#F3CA52'
        },
        fontFamily:{
            tiltWrap: 'Tilt Warp, sans-serif'
        },
        borderRadius: {
            '4xl': '2rem',
        }
      },
    },
    plugins: [],
  }