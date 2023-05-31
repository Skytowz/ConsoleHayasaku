let stompClient = null;
const currentUser = {
	"id":`${document.querySelector('meta[name="currentUser:id"]')?.content}`,
	"username":document.querySelector('meta[name="currentUser:username"]')?.content,
};
const currentRoom = {
	"id":document.querySelector('meta[name="currentRoom:id"]')?.content,
	"roomname":document.querySelector('meta[name="currentRoom:username"]')?.content,
};
const currentRoomChat = document.querySelector('#messages-container');

/**
 * 	Function called at the load of the script, used to resolve the WebSocket connection to the server.
 */
function connect() {
	const sock = new SockJS(`${window.location.protocol}//${window.location.hostname}${(window.location.port?`:${window.location.port}`:"")}/ws`);
	stompClient = Stomp.over(sock);
	stompClient.connect({}, onConnected, onError);
};

/**
 * 	Function called once the connection is initialized. Subscribing the client to the server "notifiers".
 */
function onConnected() {
	stompClient.subscribe(`/user/room/${currentRoom.id}/queue/messages`, onRoomMessageReceived);
	stompClient.subscribe(`/user/private/${currentUser.id}/queue/messages`, onPrivateMessageReceived);
	loadMessagesRoom(currentRoom.id);
};

/**
 * 	Function called to load the last messages in a room.
 * @param roomId - The room to load
 */
function loadMessagesRoom(roomId) {	
	const pelem = document.createElement("p");
	pelem.id = roomId;
	document.querySelector("#baseTR")?.appendChild(pelem);
}

/**
 * 	Function called if an error happens, disconnecting the client and server.
 */
function onError() {
	const pelem = document.createElement("p");
	pelem.innerText = "ERROR !"
	document.querySelector("#baseTR")?.appendChild(pelem);
};

/**
 * 	Function called when the WebServer sends a message to the client.
 * @param message - The message sent by the WebServer
 */
function onRoomMessageReceived(message) {
	const image = document.createElement("img");
	image.classList.add("is-rounded");
	image.src = "https://bulma.io/images/placeholders/128x128.png";
	image.alt = "Placeholder image";
	
	const figure = document.createElement("figure");
	figure.classList.add("image");
	figure.classList.add("is-32x32");
	figure.appendChild(image);
	
	const div1 = document.createElement("div");
	div1.classList.add("is-1");
	div1.classList.add("column");
	div1.appendChild(figure);
	
	const div2 = document.createElement("div");
	div2.classList.add("is-11");
	div2.classList.add("column");
	div2.innerText = JSON.parse(message.body).content;
	
	const child = document.createElement("section");
	child.classList.add("columns");
	child.classList.add("message-item");
	child.appendChild(div1);
	child.appendChild(div2);
	currentRoomChat.appendChild(child);
};

/**
 * 	Function called when the WebServer sends a private message to the client.
 */
function onPrivateMessageReceived() {
	const pelem = document.createElement("p");
	pelem.innerText = "textOfNewMessage !"
	document.querySelector("#baseTR")?.appendChild(pelem);
};

/**
 * 	Function called to send a message from the client to the WebServer.
 * @param msg - The message to send to the server
 */
function sendMessageRoom(msg) {
	if (msg.trim() !== "") {
		const message = {
			sender: currentUser.id,
			roomDestination: currentRoom.id,
			content: msg,
			date: new Date(),
		};

		stompClient.send(`/app/room/${currentRoom.id}`, {}, JSON.stringify(message));
	}
};

document.querySelector("#SendMessageForm").addEventListener('submit', e => {
	e.preventDefault();
	const message = document.querySelector("#ChatRoomMessageInput").value;
	sendMessageRoom(message);
	document.querySelector("#ChatRoomMessageInput").value = "";
});

connect();