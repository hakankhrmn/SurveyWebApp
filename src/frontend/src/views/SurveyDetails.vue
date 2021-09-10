<template>


    <div class="container">
        <div class="jumbotron mt-3">
            <h1 class="mb-5">{{ survey.surveyTopic }}</h1>
            <hr>

            <div v-for="(question, index) in survey.questions" v-bind:key="question.questionId">
                <div class="row">
                    <div class="col">
                        <h4 class="mt-5 mb-3">{{ question.questionText }}</h4>

                        <div v-for="response in question.responses"
                             v-bind:key="response.responseId"
                             class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="radio"
                                       :value="response.responseId"
                                       :name="index">
                                {{response.responseText}}
                            </label>
                        </div>


                        <button class="btn btn-primary "
                                @click="addResponse(question.questionId,responseText)">
                            Add Response
                        </button>

                        <div>
                            <mdb-container>
                                <mdb-btn color="default" @click.native="subsq = true; modalTitle = 'Update Question' ">
                                    Update Question
                                </mdb-btn>
                                <mdb-btn @click="deleteQuestion(question.questionId)" color="red">Delete Question</mdb-btn>
                                <mdb-modal :show="subsq" @close="subsq = false">
                                    <mdb-modal-header class="text-center">
                                        <mdb-modal-title tag="h4" bold class="w-100">{{modalTitle}}</mdb-modal-title>
                                    </mdb-modal-header>
                                    <mdb-modal-body class="mx-3 grey-text">
                                        <mdb-input label="Question Text" v-model="questionText" class="mb-5"/>
                                    </mdb-modal-body>
                                    <mdb-modal-footer center>
                                        <mdb-btn @click.native="subsq = false"
                                                 @click="handleQuestionOperation(question.questionId)" color="indigo">
                                            {{modalTitle}}
                                            <mdb-icon icon="paper-plane" class="ml-1"/>
                                        </mdb-btn>
                                    </mdb-modal-footer>
                                </mdb-modal>
                            </mdb-container>
                        </div>

                    </div>

                    <div class="col">
                        <mdb-container>
                            <mdb-pie-chart
                                    datalabels
                                    :data="pieChartData"
                                    :options="pieChartOptions"
                                    :width="600"
                                    :height="300"
                            />
                        </mdb-container>
                    </div>

                </div>
            </div>

            <mdb-btn color="default" @click.native="subsq = true; modalTitle = 'Add Question' ">Add Question</mdb-btn>

        </div>


    </div>

</template>

<script>
    import axios from 'axios'
    import {
        mdbPieChart,
        mdbContainer,
        mdbBtn,
        mdbModal,
        mdbIcon,
        mdbModalTitle,
        mdbModalHeader,
        mdbModalBody,
        mdbInput,
        mdbModalFooter
    } from 'mdbvue';

    export default {

        name: "SurveyDetails",
        components: {
            mdbPieChart,
            mdbContainer,
            mdbBtn,
            mdbModal,
            mdbIcon,
            mdbModalTitle,
            mdbModalHeader,
            mdbModalBody,
            mdbInput,
            mdbModalFooter
        },
        data() {
            return {

                survey: null,
                subsq: false,
                surveyId: this.$route.params.surveyId,
                modalTitle: '',
                questionIdToUpdate: 0,
                questionText: '',

                pieChartData: {
                    labels: ["A", "B", "C", "D", "E"],
                    datasets: [
                        {
                            data: [44, 55, 13, 43, 22],
                            backgroundColor: [
                                "#F7464A",
                                "#46BFBD",
                                "#FDB45C",
                                "#949FB1",
                                "#4D5360"
                            ],
                            hoverBackgroundColor: [
                                "#FF5A5E",
                                "#5AD3D1",
                                "#FFC870",
                                "#A8B3C5",
                                "#616774"
                            ]
                        }
                    ]
                },
                pieChartOptions: {
                    responsive: false,
                    maintainAspectRatio: false,
                    plugins: {
                        datalabels: {
                            color: "white",
                            align: "center",
                            font: {
                                size: 16
                            },
                            formatter: value => {
                                const [dataset] = this.pieChartData.datasets;
                                const setValue = dataset.data.reduce((a, b) => a + b);

                                return `${Math.round((value / setValue) * 100)}%`;
                            }
                        }
                    }
                }
            };

        },
        methods: {
            async getSurvey() {
                const response = await axios.get('/survey/' + this.surveyId + '/results');
                this.survey = response.data;
                console.log(response);
            },

            async addResponse(questionId, responseText) {
                const response = await axios.post('/survey' + this.surveyId + '/question/' + questionId, {
                    responseText: responseText
                });
                console.log(response);
                this.getSurvey();
            },

            async addQuestion() {
                const response = await axios.post('/survey/' + this.surveyId + '/question', {
                    questionText: this.questionText
                });
                console.log(response);
                this.getSurvey();
            },

            async updateQuestion(questionId) {
                const response = await axios.put('/survey/' + this.surveyId + '/question/' + questionId, {
                    questionText: this.questionText
                });
                console.log(response);
                this.getSurvey();
            },

            async deleteQuestion(questionId) {
                const response = await axios.delete('/survey/' + this.surveyId + '/question/' + questionId);
                console.log(response);
                this.getSurvey();
            },

            handleQuestionOperation(questionId) {
                if (this.modalTitle === "Add Question") {
                    return this.addQuestion();
                } else if (this.modalTitle === "Update Question") {
                    return this.updateQuestion(questionId);
                }
                return null;
            }

        },

        created() {
            this.getSurvey();

        }
    }
</script>

<style scoped>

</style>