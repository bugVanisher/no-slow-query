<template>
  <div
    v-loading="loading"
    :style="{
      height: height ? px(height) :'100%',
      width: width ? px(width) : '100%'
  }"/>
</template>
<script>
const ace = require('brace')
require(['emmet/emmet'], function(data) {
  window.emmet = data.emmet
})

module.exports = {
  props: {
    value: {
      required: true
    },
    wrap: String,
    fontSize: {
      type: String,
      default: '12px'
    },
    readOnly: {
      type: Boolean,
      default: false
    },
    lang: String,
    theme: String,
    height: true,
    width: true
  },
  data() {
    return {
      editor: null,
      contentBackup: '',
      loading: false
    }
  },
  watch: {
    height() {
      this.$nextTick(() => {
        this.editor.resize(true)
      })
    },
    value(val) {
      const backup = this.contentBackup
      if ((!backup || !val) || (backup && val && backup !== val)) {
        this.editor.setValue(val)
        // 程序赋值之后清除默认选中全部内容的状态
        this.editor.clearSelection()
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      const self = this
      const lang = self.lang || 'sh'
      const theme = this.theme || 'monokai'
      const wrap = this.theme || 'free'
      require('brace/ext/emmet')
      const editor = self.editor = ace.edit(self.$el)
      self.$emit('init', editor)
      editor.setReadOnly(self.readOnly)
      editor.$blockScrolling = Infinity
      editor.setOption('enableEmmet', true)
      editor.setOption('wrap', wrap)
      editor.getSession().setMode('ace/mode/' + lang)
      editor.getSession().setTabSize(4)
      editor.getSession().setUseSoftTabs(true)
      // 设置换行符模式为unix
      editor.getSession().setNewLineMode('unix')
      editor.setTheme('ace/theme/' + theme)
      editor.resize(true)
      // 初始化时如果日志量多会很慢
      this.loading = true
      setTimeout(() => {
        this.loading = false
        editor.setValue(self.value || '')
        editor.clearSelection()
        this.$emit('init-delay')
      }, 10)
      self.$el.style.fontSize = this.fontSize
      editor.on('change', function() {
        if (!self.readOnly) {
          const content = editor.getValue()
          self.contentBackup = content
          self.$emit('input', content)
        }
      })
    },
    // Form height/width string
    px(n) {
      if (/^\d*$/.test(n)) {
        return n + 'px'
      }
      return n
    },
    destroyEditor() {
      if (this.editor) {
        this.editor.destroy()
        this.editor.container.remove()
        this.editor = null
      }
    }
  },
  beforeDestroy() {
    // Destroy the ace editor instance or may lead to memory leak.
    this.destroyEditor()
  }
}
</script>
