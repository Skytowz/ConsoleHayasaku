// Initialize all input of date type.
bulmaCalendar.attach('[type="date"]', {
	type:"date", 
	dateFormat:"dd/MM/yyyy", 
	lang:(navigator.language.substring(0, 2) || "en"),
	startDate:new Date(),
	showClearButton: false
});

// Loop on each calendar initialized
// calendars.forEach(calendar => {
	// Add listener to select event
// 	calendar.on('select', date => {
// 	});
// });

// To access to bulmaCalendar instance of an element
// const element = document.querySelector('.calendar');
// if (element) {
	// bulmaCalendar instance is available as element.bulmaCalendar
// 	element.bulmaCalendar.on('select', datepicker => {
// 	});
// }