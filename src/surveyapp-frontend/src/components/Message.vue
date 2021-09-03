<template>
    <div v-if="message" class="alert alert-info" role="alert">
        {{message}}
    </div>
</template>

<script>
    export default {
        name: "Message",
        props: ['message']
    }
</script>