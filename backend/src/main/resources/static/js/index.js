const socket = new WebSocket("ws://localhost:8080/connect");
const Client = Stomp.over(socket);
const projectId = sessionStorage.getItem("projectId");


function openPopup() {
    const popup = document.getElementById("popup");
    popup.classList.remove("hidden");
}

function openChat() {
    const popup = document.getElementById("popup");
    const chatContainer = document.getElementById("chatContainer");
    const usernameInput = document.getElementById("usernameInput").value;
    const projectInput = document.getElementById("projectInput").value;
    const usuarioInput = document.getElementById("usuarioInput").value;
    if (usernameInput !== "") {
        popup.style.display = "none";
        chatContainer.classList.remove("hidden");
        sessionStorage.setItem("user", usernameInput);
        sessionStorage.setItem("userId",usuarioInput);
        sessionStorage.setItem("projectId",projectInput);
    } else {
        alert("Digite um nome válido!");
    }
}

function sendMessage(e) {
    e.preventDefault();
    const messageInput = document.getElementById("messageInput").value;


    const message = {
        sender: sessionStorage.getItem("userId"),
        content: messageInput,
    };
    console.log(projectId);
    Client.send("/chat/project/" + projectId, {}, JSON.stringify(message));

    document.getElementById("messageInput").value = "";
}

function displayMessage(content, nickname) {
    const chatMessages = document.getElementById("chatMessages");
    const messageElement = document.createElement("div");

    messageElement.textContent = nickname + ": " + content;
    chatMessages.appendChild(messageElement);
}

function connect(){
    Client.connect({}, function (frame) {
        console.log('Conectado: ' + frame);
        console.log(projectId);
        Client.subscribe('/project/' + projectId, function (message) {
            const chatMessage = JSON.parse(message.body);
                displayMessage(chatMessage.content, chatMessage.sender.nickname);
        });
    });
}


// function getMessages(){
//
// // Configuração da requisição
//     const requestOptions = {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     };
//     fetch(base, requestOptions)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Erro ao fazer a requisição');
//             }
//             return response.json();
//         })
//         .then(data => {
//             console.log('Resposta da API:', data);
//         })
//         .catch(error => {
//             console.error('Erro:', error);
//         });
//
// }
connect();
openPopup();
