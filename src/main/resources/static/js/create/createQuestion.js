//show createQuestionModal
let addQuestionBtn = window.document.getElementById("createQuestionBtn")
let questionCreate = window.document.getElementById("createQuestionModal")
let spanQuestion = document.getElementById("closeQuestion");
addQuestionBtn.onclick = function() {
    questionCreate.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
spanQuestion.onclick = function() {
    questionCreate.style.display = "none";
}

//true false pentru multipleChoice
let questionIsMultipleChoiceTrue = window.document.getElementById("questionIsMultipleChoiceTrue")
let questionIsMultipleChoiceFalse = window.document.getElementById("questionIsMultipleChoiceFalse")

questionIsMultipleChoiceFalse.onclick = function() {
    if (questionIsMultipleChoiceTrue.checked){
        questionIsMultipleChoiceTrue.checked = false
    }
    questionIsMultipleChoiceFalse.checked = true
}
questionIsMultipleChoiceTrue.onclick = function() {
    if (questionIsMultipleChoiceFalse.checked){
        questionIsMultipleChoiceFalse.checked = false
    }
    questionIsMultipleChoiceTrue.checked = true
}


//adaugare option table
let addOptionBtn = document.getElementById("addOptionBtn");
addOptionBtn.onclick = function () {
    var option = document.getElementById('questionCreateOption');
    var itemText = option.value;
    if (itemText.trim() !== '') {
        // Create a new row and two cells
        var table = document.getElementById('optionTableBody');
        var newRow = table.insertRow(0); // Insert at the beginning
        var cell1 = newRow.insertCell(0);
        var cell2 = newRow.insertCell(1);

        // Set the cell values
        cell1.innerHTML = itemText;
        cell2.innerHTML = '<button class="btn" onclick="deleteRow(this)">X</button>';

        // Clear the input field
        option.value = '';
    } else {
        alert('Please enter an item.');
    }
}
function deleteRow(button) {
    var row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
}


// adaugare question table
let questionTable = window.document.getElementById("questionTable")
let saveQuestionBtn = window.document.getElementById("saveQuestionBtn")
saveQuestionBtn.onclick = function () {
    let optionElements = window.document.getElementById("optionTable").querySelectorAll("tr td:nth-child(1)")
    let description = window.document.getElementById("questionCreateDescription")
    let hint = window.document.getElementById("questionCreateHint")
    let multipleChoice = window.document.getElementById("questionIsMultipleChoiceTrue")
    let options = []
    optionElements.forEach(function (cell){
        options.push(cell.textContent)
    })

    let question = {
        description: description.value,
        hint: hint.value,
        multipleChoice: multipleChoice.checked,
        options: options
    }

    //POST AJAX /create/question aici

    //pentru test completeaza tabelul in loc de POST AJAX
    let body = document.getElementById("questionTableBody")
    let row = body.insertRow(body.rows.length)
    var cell1 = row.insertCell(0)
    var cell2 = row.insertCell(1)
    var cell3 = row.insertCell(2)
    var cell4 = row.insertCell(3)
    var cell5 = row.insertCell(4)
    cell1.innerHTML = question.description
    cell2.innerHTML = question.hint
    cell3.innerHTML = question.options.toString()
    cell4.innerHTML = question.multipleChoice
    cell5.innerHTML = '<button class="btn" onclick="deleteRow(this)">X</button>';


    //eliberare inputuri de completat
    description.value = ''
    hint.value = ''
    multipleChoice.value = ''
    let rows = document.getElementById("optionTableBody").querySelectorAll("tr")
    rows.forEach(function (row){
        row.parentNode.removeChild(row)
    })

    //inchidem questionModal
    questionCreate.style.display = "none"
}

