/**
 * Will open a modal div of the given profile
 * @param id - The ID of the user to shod
 */
function openModalProfile(id){
	fetch(`/api/profilePictures/${id}`, () => {
		document.querySelector("#pictureToEdit")?.show()
	});
}

document.querySelector("#profile-button").addEventListener("click", e => {
	e.preventDefault();
	openModalProfile(document.querySelector('meta[name="currentUser:id"]')?.content);
})

document.querySelectorAll(".purpletag").forEach(tag => {
	tag.querySelectorAll(".rate").forEach(rate => {
		rate.addEventListener('click', event => {
			event.preventDefault();
			tag.querySelectorAll(".rate").forEach(e => (!e.classList['is-light'])?e.classList.add('is-light'):"");
			rate.classList.remove('is-light')
		})
	})
})