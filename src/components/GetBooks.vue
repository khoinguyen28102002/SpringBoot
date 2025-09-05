<template>
    <div id="wrapper">
        <div v-for="book in allBooks" :key="book.id">
            <h2> {{ book.name }} </h2>
            <p> Author: {{ book.author }}</p>
            <p> Price: {{ book.price }}</p>
            <button @click="countBook(book.id)">+</button>
            <p> You choose {{ book.count }} book</p>

        </div>
        <ul>
            <li v-for="book in allBooks.filter(book => book.name === findName)" :key="book.id">
                <p>Number id: {{ book.id }}</p>
                <p>Name: {{ book.name }}</p>
                <p>Author: {{ book.author }}</p>
                <p>Price: {{ book.price }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
    export default{
        // data(){
        //     return {
        //         numberBook: this.
        //     }
        // },
        props: {
            allBooks: Array
        },
        methods:{
            countBook(id){
                this.$emit('count-book', id)
            }
        }


    }
</script>

<style>
#wrapper {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

#wrapper > div {
  border: dashed black 1px;
  margin: 10px ;
  padding: 10px;
  max-width: 300px;
  height: 220px;
  background-color: lightgreen;
}
</style>