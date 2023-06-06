document.addEventListener('DOMContentLoaded', () => {
	document.querySelectorAll(".submit-form").forEach(div => {
		div.addEventListener("click",  e => {
			e.preventDefault();
			div.parentElement.submit();
		})
	})
});