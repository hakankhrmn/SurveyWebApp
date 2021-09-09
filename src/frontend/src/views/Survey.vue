<template>
    <div>


        <div id="app">
            <div class="container">
                <div class="jumbotron mt-3">
                    <h1 class="mb-5">{{ survey.surveyTopic }}</h1>
                    <hr>
                    <p v-if="errors[questionIndex]" class="alert alert-danger">
                        {{ error }}
                    </p>
                    <div v-for="(question, index) in survey.questions" v-bind:key="question.questionId">
                        <div v-show="index === questionIndex">

                            <h4 class="mt-5 mb-3">{{ question.questionText }}</h4>

                            <div v-for="response in question.responses" v-bind:key="response.responseId"
                                 class="form-check">

                                <label class="form-check-label">
                                    <input class="form-check-input" type="radio"
                                           :value="response.responseId"
                                           :name="index"
                                           v-model="replies[index]"
                                           v-on:change="getValue($event)">
                                    {{response.responseText}}
                                </label>

                            </div>

                            <div class="mt-5">
                                <button
                                        class="btn btn-primary"
                                        v-if="questionIndex > 0"
                                        @click="prev">
                                    prev
                                </button>
                                <button class="btn btn-secondary" @click="next">
                                    next
                                </button>
                            </div>
                        </div>
                    </div>

                    <div v-show="questionIndex === survey.questions.length">
                        <button class="btn btn-secondary" @click="submit">
                            submit
                        </button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Survey",
        data() {
            return {
                survey: null,
                surveyId: this.$route.params.surveyId,
                questionIndex: 0,
                replies: [],
                responses: [],
                responseId: 0,
                errors: [],
                error: "",
                ele: []
            }
        },
        methods: {
            async getSurvey() {
                const response = await axios.get('/survey/' + this.surveyId);
                this.survey = response.data;
                console.log(response);
            },
            prev() {
                this.responses.pop();
                this.questionIndex--;
            },

            next() {
                if (this.replies[this.questionIndex] === undefined) {
                    this.errors[this.questionIndex] = 1;
                    this.error = "Please select your answer";
                } else {
                    this.errors[this.questionIndex] = 0;
                    this.responses.push(this.responseId);
                    console.log(this.responses);
                    this.questionIndex++;
                }

            },

            getValue(event) {
                this.responseId = event.target.value;
                console.log(this.responseId);
            },

            async submit() {
                console.log(this.responses);
                const response = await axios.post('/survey/' + this.survey.surveyId + '/results', {
                    responseIds: this.responses
                });
                console.log(response);
            }
        },


        created() {
            this.getSurvey();
        }
    }
</script>

<style scoped>

</style>