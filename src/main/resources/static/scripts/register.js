document.querySelector("[name='username']").addEventListener("input", () => {
	document.querySelector("#todo").appendChild(document.createElement("p"))
});

document.querySelector("[name='email']").addEventListener("input", () => {
	document.querySelector("#todo").appendChild(document.createElement("p"))
});