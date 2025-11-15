const transportSelect = document.getElementById("transport");
const seatClassDiv = document.getElementById("seatClassDiv");
const seatClass = document.getElementById("seatClass");

const modifyDiv = document.getElementById("modifyStopsDiv");
const modifyInput = document.getElementById("modifyStopsInput");
const modifyBtn = document.getElementById("modifyStopsBtn");

const resultDiv = document.getElementById("result");
const priceAlertDiv = document.getElementById("priceAlert");

// Seat class visible only for plane
transportSelect.addEventListener("change", () => {
    seatClassDiv.style.display = transportSelect.value === "plane" ? "block" : "none";
});

// Routes
class RouteStrategy { generateStops() { return []; } }
class RelaxationRoute extends RouteStrategy { generateStops() { return ["Beach", "Spa", "Local Cafe", "Park"]; } }
class AdrenalineRoute extends RouteStrategy { generateStops() { return ["Bungee Jump", "White Water Rafting", "Climbing", "Zipline"]; } }
class RomanceRoute extends RouteStrategy { generateStops() { return ["Romantic Dinner", "Boat Ride", "Sunset Viewpoint", "Wine Tasting"]; } }

// Transport & Booking
class Transport { constructor(baseCost) { this.baseCost = baseCost; } }
class Plane extends Transport { constructor(seatClass) { super(500); this.baseCost += seatClass === "business" ? 200 : 0; } }
class Car extends Transport { constructor() { super(500); this.baseCost += 100; } }
class Cruise extends Transport { constructor() { super(500); this.baseCost += 300; } }

class Booking {
    constructor(transport) { this.transport = transport; this.cost = transport.baseCost; }
    addMeal() { this.cost += 50; }
    addInsurance() { this.cost += 30; }
    calculateTotalCost() { return this.cost; }
}

// PriceSubject (observer)
class PriceSubject {
    constructor(price) { this.price = price; this.observers = []; }
    registerObserver(observer) { this.observers.push(observer); }
    setPrice(newPrice) { this.price = newPrice; this.notifyObservers(); }
    notifyObservers() { this.observers.forEach(obs => obs.update(this.price)); }
}

class UserObserver {
    constructor(name) { this.name = name; }
    update(price) {
        priceAlertDiv.classList.remove("hidden");
        priceAlertDiv.innerHTML = `Hey ${this.name}, the price has dropped! New price: $${price}`;
    }
}

// Form submit
document.getElementById("travelForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const name = document.getElementById("userName").value;
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;
    const transportVal = transportSelect.value;
    const seat = transportVal === "plane" ? seatClass.value : "-";
    const dateTo = document.getElementById("dateTo").value;
    const dateBack = document.getElementById("dateBack").value || "One-way";
    const meal = document.getElementById("meal").value;
    const insurance = document.getElementById("insurance").value;
    const mood = document.getElementById("mood").value.toLowerCase();

    // Transport object
    let transportObj;
    if (transportVal === "plane") transportObj = new Plane(seat);
    else if (transportVal === "car") transportObj = new Car();
    else transportObj = new Cruise();

    // Booking
    const booking = new Booking(transportObj);
    if (meal === "Yes") booking.addMeal();
    if (insurance === "Yes") booking.addInsurance();

    // PriceSubject demo
    const priceSubject = new PriceSubject(booking.calculateTotalCost());
    const userObserver = new UserObserver(name);
    priceSubject.registerObserver(userObserver);

    // Route object
    let routeObj;
    switch (mood) {
        case "relaxation": routeObj = new RelaxationRoute(); break;
        case "adrenaline": routeObj = new AdrenalineRoute(); break;
        case "romance": routeObj = new RomanceRoute(); break;
    }
    let stops = routeObj.generateStops();

    const printSummary = () => {
        resultDiv.classList.remove("hidden");
        resultDiv.innerHTML = `
            <h3>Trip Summary</h3>
            <p><strong>Name:</strong> ${name}</p>
            <p><strong>From:</strong> ${origin}</p>
            <p><strong>Destination:</strong> ${destination}</p>
            <p><strong>Transport:</strong> ${transportVal}</p>
            <p><strong>Seat Class:</strong> ${seat}</p>
            <p><strong>Meal:</strong> ${meal}</p>
            <p><strong>Insurance:</strong> ${insurance}</p>
            <p><strong>Departure Date:</strong> ${dateTo}</p>
            <p><strong>Return Date:</strong> ${dateBack}</p>
            <p><strong>Mood:</strong> ${mood}</p>
            <p><strong>Planned Stops:</strong> ${stops.join(", ")}</p>
            <p><strong>Estimated Total Cost:</strong> $${booking.calculateTotalCost()}</p>
        `;
    };

    printSummary();

    // Modify planned stops
    modifyDiv.classList.remove("hidden");
    modifyBtn.onclick = () => {
        const newStops = modifyInput.value.split(",").map(s => s.trim());
        if (newStops.length > 0 && newStops[0] !== "") stops = newStops;
        printSummary();
    };

    // Simulate price drop after 2 seconds
    setTimeout(() => {
        priceSubject.setPrice(priceSubject.price - 50);
    }, 2000);
});
