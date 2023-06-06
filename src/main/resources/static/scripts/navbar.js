document.addEventListener('DOMContentLoaded', () => {
	document.querySelector("#logout").addEventListener("click", e => {
		e.preventDefault();
		document.logout.submit();
	})
});