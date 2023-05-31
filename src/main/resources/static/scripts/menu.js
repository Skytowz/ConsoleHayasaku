document.querySelectorAll(".tabs a:not(#hide-menu)").forEach(e => {
	e.addEventListener('click', f => {
		f.preventDefault();
		document.querySelectorAll(".tabs li").forEach(g => g.classList.remove('is-active'))
		e.parentNode.classList.add('is-active');
		document.querySelectorAll(".menu-content").forEach(g => g.classList.add("is-hidden"));
		document.querySelector(`#${e.getAttribute('data-content')}-content`).classList.remove("is-hidden");
	})
})

document.querySelector("#hide-menu").addEventListener('click', e => {
	e.preventDefault();
	if(document.querySelector("#side-menu").classList.contains("is-1")) {
		document.querySelector("#side-menu").classList.remove("is-1");
		document.querySelector("#side-menu").classList.add("is-2");
		document.querySelector("#side-menu-text").classList.remove("is-hidden");
		document.querySelectorAll(".tabs li:not(#hide-menu-li)").forEach(e2 => { e2.classList.remove("is-hidden") });
	} else {
		document.querySelector("#side-menu").classList.remove("is-2");
		document.querySelector("#side-menu").classList.add("is-1");
		document.querySelector("#side-menu-text").classList.add("is-hidden");
		document.querySelectorAll(".tabs li:not(#hide-menu-li)").forEach(e2 => { e2.classList.add("is-hidden") });
	}
})

document.querySelector("#searchRoomInput").addEventListener('input', e => {
	e.preventDefault();
	document.querySelectorAll(".room-panel-block").forEach(roomPanel => {
		if (roomPanel.dataset.roomname.includes(document.querySelector("#searchRoomInput").value)){
			roomPanel.classList.remove('is-hidden');
		} else {
			roomPanel.classList.add('is-hidden');
		}
	})
})