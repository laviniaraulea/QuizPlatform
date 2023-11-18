// true false pentru seeResult
let quizSeeResultTrue = window.document.getElementById("quizSeeResultTrue")
let quizSeeResultFalse = window.document.getElementById("quizSeeResultFalse")

quizSeeResultFalse.onclick = function() {
    if (quizSeeResultTrue.checked){
        quizSeeResultTrue.checked = false
    }
    quizSeeResultFalse.checked = true
}
quizSeeResultTrue.onclick = function() {
    if (quizSeeResultFalse.checked){
        quizSeeResultFalse.checked = false
    }
    quizSeeResultTrue.checked = true
}


//show createQuiz Modal
let addQuizBtn = window.document.getElementById("createQuizBtn")
let quizCreate = window.document.getElementById("createQuizModal")
let spanQuiz = document.getElementById("closeQuiz");
addQuizBtn.onclick = function() {
    quizCreate.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
spanQuiz.onclick = function() {
    quizCreate.style.display = "none";
}


// adaugare quiz table
let saveQuizBtn = window.document.getElementById("saveQuizBtn")
saveQuizBtn.onclick = function (){
    let body = document.getElementById("quizTableBody")
    let category = document.getElementById("quizCategory")
    let difficulty = document.getElementById("quizDifficulty")
    let timeLimit = document.getElementById("quizTimeLimit")
    let seeResult = document.getElementById("quizSeeResultTrue")
    let description = document.getElementById("quizDescription")
    let minimumScore =document.getElementById("quizMinimumScore")
    let minimumRequired = false
    if (minimumScore.value){
        minimumRequired = true
    }


    let quiz = {
        id: body.rows.length,
        //user: username
        category: category.value,
        difficulty: difficulty.value,
        timeLimit: timeLimit.value,
        seeResult: seeResult.checked,
        description: description.value,
        passingScore: minimumScore.value,
        minimumScoreRequired: minimumRequired

    }
    console.log(quiz)

    //AJAX POST /create/quiz aici

    //doar de test completez tabelul, in loc de AJAX POST
    let row = body.insertRow(body.rows.length)
    var cell1 = row.insertCell(0)
    var cell2 = row.insertCell(1)
    var cell3 = row.insertCell(2)
    var cell4 = row.insertCell(3)
    var cell5 = row.insertCell(4)
    var cell6 = row.insertCell(5)
    var cell7 = row.insertCell(6)
    cell1.innerHTML = quiz.category
    cell2.innerHTML = quiz.difficulty
    cell3.innerHTML = quiz.timeLimit
    cell4.innerHTML = quiz.seeResult
    cell5.innerHTML = quiz.description
    cell6.innerHTML = quiz.passingScore
    cell7.innerHTML = '<button class="btn" onclick="deleteRow(this)">X</button>'

    timeLimit.value = ""
    seeResult.checked = false
    document.getElementById("quizSeeResultFalse").checked = false
    description.innerText = ""
    minimumScore.value = ""

    quizCreate.style.display = "none"

}


