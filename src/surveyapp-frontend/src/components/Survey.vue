<template>

<div class = "container">
    <h1 class="text-center">Survey List </h1>
    <table class="table table-striped">
        <thead>
            <th> Survey Id </th>
            <th> Survey Topic </th>
        </thead>
        <tbody>
            <tr v-for = "survey in surveys" v-bind:key = "survey.surveyId">
                <td> {{survey.surveyId}} </td>
                <td> {{survey.surveyTopic}} </td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script>
import axios from "axios";

export default {
    name: 'Survey',
    data(){
        return {
            surveys : []
        }
    },
   
    methods: {
        getSurveys(){
            axios.get('/survey').then((response) => {
                this.surveys = response.data;
            })
        }
    },

    created() {
        this.getSurveys();
    }
    

}
</script>
