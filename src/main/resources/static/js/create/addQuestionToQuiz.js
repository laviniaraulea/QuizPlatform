var quizTableSelect = document.getElementById("quizTableBody");
var selectedQuizId = 0

quizTableSelect.addEventListener("click", function (event) {
    var target = event.target; // The clicked element (cell)

    if (target.tagName === "TD") {
        var row = target.closest("tr");
        row.style.backgroundColor = "lightblue";
    }

    selectedQuizId = row.rowIndex

    console.log(selectedQuizId)

});

var questionTableSelect = document.getElementById("questionTableBody");
var selectedQuestionId = 0

questionTableSelect.addEventListener("click", function (event) {
    var target = event.target; // The clicked element (cell)

    if (target.tagName === "TD") {
        var row = target.closest("tr");
        row.style.backgroundColor = "lightblue";
    }

    selectedQuestionId = row.rowIndex

    console.log(selectedQuestionId)
});

var addQuestionToQuiz = document.getElementById("addQuestionBtn")

addQuestionToQuiz.onclick = function (){
    //POST AJAX  /add/question
}