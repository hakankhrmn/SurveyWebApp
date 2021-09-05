<template>

<div class = "container">

    <h1 class="text-center">Survey List </h1>
    <table class="table table-striped">
        <thead>
            <th> Survey Id </th>
            <th> Survey Topic </th>
            <th> Show Survey </th>
            <th> Delete Topic </th>
        </thead>
        <tbody>
            <tr v-for = "survey in surveys" v-bind:key = "survey.surveyId">
                <td> {{survey.surveyId}} </td>
                <td> {{survey.surveyTopic}} </td>
                <td> <mdb-btn color="blue" @click="showSurvey(survey.surveyId)">Show Survey</mdb-btn> </td>
                <td> <mdb-btn color="red" @click="deleteSurvey(survey.surveyId)">Delete Survey</mdb-btn> </td>
            </tr>
        </tbody>
    </table>

    <div>
    <mdb-container>
    <mdb-btn color="default" @click.native="subs = true">Add Survey</mdb-btn>
    <mdb-modal :show="subs" @close="subs = false">
      <mdb-modal-header class="text-center">
        <mdb-modal-title tag="h4" bold class="w-100">Add Survey</mdb-modal-title>
      </mdb-modal-header>
      <mdb-modal-body class="mx-3 grey-text">
        <mdb-input label="Survey Topic" v-model="surveyTopic" class="mb-5"/>
      </mdb-modal-body>
      <mdb-modal-footer center>
        <mdb-btn @click.native="subs = false" @click="addSurvey" color="indigo">Add <mdb-icon icon="paper-plane" class="ml-1"/></mdb-btn>
      </mdb-modal-footer>
    </mdb-modal>
  </mdb-container>
  </div>
</div>
</template>

<script>
import { mdbContainer, mdbBtn, mdbModal, mdbIcon, mdbModalHeader, mdbModalBody, mdbInput, mdbModalFooter } from 'mdbvue';
import axios from "axios";

export default {
    name: 'Surveys',

    components: {
        mdbContainer,
        mdbBtn,
        mdbModal,
        mdbModalHeader,
        mdbModalBody,
        mdbInput,
        mdbModalFooter,
        mdbIcon
    },
  
    data(){
        return {
            subs: false,
            surveys : [],
            surveyTopic: ''
        }
    },
   
    methods: {
        getSurveys(){
            axios.get('/survey').then((response) => {
                this.surveys = response.data;
            })
        },

        async addSurvey() {
            const response = await axios.post('/survey',{
                surveyTopic: this.surveyTopic
            });
            console.log(response);
            this.$router.go();
        },

        async deleteSurvey(surveyId) {
            const response = await axios.delete('/survey/' + surveyId);
            console.log(response);
            this.$router.go();
        },

        async showSurvey(surveyId) {
            this.$router.push('/survey/' + surveyId)
        }
    },

    created() {
        this.getSurveys();
    }
    

}
</script>
