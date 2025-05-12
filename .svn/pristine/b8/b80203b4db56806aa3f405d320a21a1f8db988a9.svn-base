function formatNumber(number) {
    return number < 10 ? '0' + number : number;
}

function updateClock() {
    const now = new Date();
    const hours = formatNumber(now.getHours());
    const minutes = formatNumber(now.getMinutes());
    const seconds = formatNumber(now.getSeconds());
    const date = now.toLocaleDateString();

    const clockElement = document.getElementById("clock");
    clockElement.innerHTML = `
        <div>${date}</div>
        <div>${hours}:${minutes}:${seconds}</div>
    `;
}

updateClock();

setInterval(updateClock, 7000);