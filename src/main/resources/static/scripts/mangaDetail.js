
document.addEventListener('DOMContentLoaded', () => {
	const edit = document.querySelector("input[name='edit']");
	const send = document.querySelector("input[name='send']");
	const cancel = document.querySelector("input[name='cancel']");
	const inputs = document.querySelectorAll("#form input");
	const hidden = document.querySelectorAll('.is-hidden');
	const removeButton = document.querySelectorAll(".remove-command");
	const addButton = document.querySelector("#add-command");
	const templateCommand = document.querySelector("#template-command")

	const mode = document.querySelector("input[name='mode']");

	if(mode.value == "update"){
				console.log("test");

		edit.addEventListener("click", e => {
					console.log("test");
			e.preventDefault();
			edit.type = "hidden";
			send.type = "button";
			cancel.type = "button";
			inputs.forEach(input => input.disabled = false);
			hidden.forEach(icon => icon.classList.remove("is-hidden"));
		})
		
		cancel.addEventListener("click", e => {
			location.reload();
		})
		
	}
	send.addEventListener("click",e => {
		e.preventDefault();
		document.querySelector('#form').parentElement.submit();
	})
	
	removeButton.forEach(eventRemove)
	
	addButton.addEventListener("click", e => {
		e.preventDefault();
		const clone = document.importNode(templateCommand.content,true);
		addButton.parentElement.parentElement.insertBefore(clone,addButton.parentElement)
		const button = document.querySelectorAll(".remove-command");
		button.forEach(eventRemove);
	})
	
});

const eventRemove = button => {
		button.addEventListener("click", e => {
			e.preventDefault();
			button.parentElement.remove();
		})
	}
