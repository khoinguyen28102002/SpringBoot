<template>
  <img alt="Vue logo" src="./assets/logo.png" style="display: block; margin: auto;">
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
   <get-books :all-books="Books"
      :find-name="bookName"
      @count-book="countBookNum"/>
   <input type="text" v-model="bookName" />
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  // components: {
  //   HelloWorld
  // }
  data (){
    return {
      bookName: '',
      Books: [
          {id: 1, name: 'Cannot Hurt Me', author: 'David Gogin', price: '250.000VND', count: 0},
          {id: 2, name: 'How to Win Friends and Influence People', author: 'Dale Carnegie', price: '180.000VND', count: 0},
          {id: 3, name: 'Thinks and Grow Rich', author: 'Napoleon Hill', price: '220.000VND', count: 0},
          {id: 4, name: 'The 7 Habits of Highly Effective People', author:'Stephen R. Covey', price: '300.000VND', count: 0},
          {id: 5, name: 'The Greatest Salesman in the World', author: 'Og Mandino', price: '150.000VND', count: 0}
      ]
    }
  },
  methods:{
    countBookNum(book_id){
      const book = this.Books.find(b => b.id === book_id)
      // alert(book_id)
      if(book){
        book.count++
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
