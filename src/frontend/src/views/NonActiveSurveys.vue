<template>

    <div class="container">

        <h1 class="text-center">Survey List </h1>
        <br/>
        <mdb-tbl striped>
            <mdb-tbl-head color="black" textWhite>
                <tr>
                    <th> Survey Topic</th>
                    <th> Activate</th>
                <tr/>
            </mdb-tbl-head>
            <mdb-tbl-body>
                <tr v-for="survey in surveys" v-bind:key="survey.surveyId">
                    <td> {{survey.surveyTopic}}</td>
                    <td>
                        <mdb-btn color="green" @click="activateSurvey(survey.surveyId)">Activate Survey</mdb-btn>
                    </td>
                </tr>
            </mdb-tbl-body>
        </mdb-tbl>

        <br/>

        <mdb-btn color="default" @click.native="goSurveys">Show Active Surveys</mdb-btn>

    </div>
</template>

<script>
    import {mdbBtn, mdbTbl, mdbTblHead, mdbTblBody} from 'mdbvue';
    import axios from 'axios'

    export default {
        name: "NonActiveSurveys",
        components: {
            mdbBtn,
            mdbTbl,
            mdbTblHead,
            mdbTblBody,
        },

        data() {
            return {
                surveys: []
            }
        },

        methods: {
            async goSurveys() {
                this.$router.push('/survey');
            },

            async getNonActiveSurveys() {
                const response = await axios.get('/survey/nonactives');
                this.surveys = response.data;

            },
            async activateSurvey(surveyId) {
                const response = await axios.put('/survey/nonactives/' + surveyId);
                console.log(response);
                this.getNonActiveSurveys();
            }
        },

        created() {
            this.getNonActiveSurveys();
        },

    }
</script>

<style scoped>

</style>