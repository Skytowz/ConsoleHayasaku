document.addEventListener('DOMContentLoaded', () => {
	// Functions to open and close a modal
	function openModal($el) {
		closeAllModals();
		$el.classList.add('is-active');
	}
	// Will close the given modal
	function closeModal($el) {
		$el.classList.remove('is-active');
	}
	// Will close all modals (remove "is-active" class from all ".modal")
	function closeAllModals() {
		(document.querySelectorAll('.modal') || []).forEach(($modal) => {
			closeModal($modal);
		});
	}

	// Add a click event on buttons to open a specific modal
	(document.querySelectorAll('.js-modal-trigger') || []).forEach(($trigger) => {
		const modal = $trigger.dataset.target;
		const $target = document.getElementById(modal);
		

		$trigger.addEventListener('click', () => {
			openModal($target);
		});
	});

	// Add a click event on various child elements to close the parent modal
	(document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
		const $target = $close.closest('.modal');

		$close.addEventListener('click', () => {
			closeModal($target);
		});
	});

	// Add a keyboard event to close all modals
	document.addEventListener('keydown', (event) => {
		const ek = event || window.event;

		if (ek.keyCode === 27) { // Escape key
			closeAllModals();
		}
	});

	document.querySelectorAll(".signout-link").forEach(e => e.addEventListener('click', event => {
		event.preventDefault();
		document.querySelector("#signout-form").submit();
	}));
	document.querySelectorAll(".open-calendar").forEach(e => e.addEventListener('click', event => {
		event.preventDefault();
		openModal(document.querySelector(".calendar-modal"));
	}));
	document.querySelectorAll(".open-login").forEach(e => e.addEventListener('click', event => {
		event.preventDefault();
		openModal(document.querySelector(".login-modal"));
	}));
	document.querySelectorAll(".open-register").forEach(e => e.addEventListener('click', event => {
		event.preventDefault();
		openModal(document.querySelector(".register-modal"));
	}));
});