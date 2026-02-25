import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const lightTheme = {
  dark: false,
  colors: {
    background: '#F9FAFB',
    surface: '#FFFFFF',
    primary: '#4F86F7',
    secondary: '#7BD389',
    success: '#4CAF50',
    warning: '#FFC107',
    error: '#EF5350',
  }
}

export default createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'lightTheme',
    themes: {
      lightTheme
    }
  }
})