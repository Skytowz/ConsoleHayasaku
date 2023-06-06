document.addEventListener('DOMContentLoaded', () => {
	const edit = document.querySelector("input[name='edit']");
	const send = document.querySelector("input[name='send']");
	const cancel = document.querySelector("input[name='cancel']");
	const inputs = document.querySelectorAll("#form input");

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
		})
		
		cancel.addEventListener("click", e => {
			e.preventDefault();
			edit.type = "button";
			send.type = "hidden";
			cancel.type = "hidden";
			inputs.forEach(input => input.disabled = true);
		})
		
	}
	send.addEventListener("click",e => {
		e.preventDefault();
		document.querySelector('#form').parentElement.submit();
	})
	
});


