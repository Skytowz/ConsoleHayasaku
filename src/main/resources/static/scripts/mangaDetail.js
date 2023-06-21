
document.addEventListener('DOMContentLoaded', () => {
	const edit = document.querySelector("input[name='edit']");
	const send = document.querySelector("input[name='send']");
	const cancel = document.querySelector("input[name='cancel']");
	const inputs = document.querySelectorAll("#form input");
	const hidden = document.querySelectorAll('.is-hidden');

	const mode = document.querySelector("input[name='mode']");

	if(mode.value == "update"){
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
});
