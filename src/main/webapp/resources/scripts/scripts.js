const url = "/api";
let points = [];

/*function submitForm(event) {
    event.preventDefault();
    document.querySelectorAll(".error").forEach(el => el.remove());
    const x = document.querySelector('input[type="radio"]:checked');
    const y = document.getElementById("y");
    const sliderInstance = ice.ace.instance('frm:sliderR');
    const r = sliderInstance.getValue();

    if (!x) {
        createError("x не определен");
    } else if (!r) {
        createError("r не выбран");
    } else {
        y.classList.remove("wrong");
        Promise.all([
            checkY(y),
            checkR(r)
        ]).then(() => {
            sendData(x.value, y.value, r.value);
            redrawPoints();
        }).catch((error) => {
            createError(error);
        });
    }
}*/

const checkY = (value) => {
    return new Promise((resolve, reject) => {
        if ((-3) > value.value || value.value > 5) {
            value.classList.add("wrong");
            reject("y вне допустимого радиуса");
        } else {
            resolve();
        }
    });
}

const checkR = (arr) => {
    return new Promise((resolve, reject) => {
        if (!arr) {
            reject("r не выбран");
        } else {
            resolve();
        }
    });
}

const createError = (message) => {
    const error = document.createElement("p");
    error.className = "error";
    error.textContent = message;
    document.getElementById("coordInputs").prepend(error);
}

/*function sendData(x, y, r) {
    const queryString = new URLSearchParams({
        x: parseFloat(x),
        y: parseFloat(y),
        r: parseFloat(r)
    }).toString();
    fetch(`${url}?${queryString}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 400) {
                    createError("Неверные параметры");
                } else if (response.status === 405) {
                    createError("Запрещенный метод");
                }
            } else {
                return response.json();
            }
        })
        .then(data => {
            console.log(data);
            addToTable(data.x.toFixed(2), data.y.toFixed(2), data.r, data.status, new Date().toLocaleTimeString(), data.timeOfCalculating);
            drawDot(data.x, data.y, data.r, data.status);
            points.push({x: data.x, y: data.y, r: data.r, status: data.status});
        })
        .catch(error => {
            console.log(error);
        });
}

function addToTable(x, y, r, status, time, data) {
    const tableBody = document.getElementById("table-body");
    const row = document.createElement("tr");
    row.className = "row";
    const xtd = document.createElement("td");
    xtd.className = "item";
    xtd.textContent = x;
    row.appendChild(xtd);
    const ytd = document.createElement("td");
    ytd.className = "item";
    ytd.textContent = y;
    row.appendChild(ytd);
    const rtd = document.createElement("td");
    rtd.className = "item";
    rtd.textContent = r;
    row.appendChild(rtd);
    const stattd = document.createElement("td");
    stattd.className = "item";
    stattd.textContent = status;
    row.appendChild(stattd);
    const timetd = document.createElement("td");
    timetd.className = "item";
    timetd.textContent = time;
    row.appendChild(timetd);
    const datatd = document.createElement("td");
    datatd.className = "item";
    datatd.textContent = data;
    row.appendChild(datatd);
    tableBody.prepend(row);
}*/

function drawDot(x, y, r, status) {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    const pixelX = centerX + (x * (canvas.width / 12));
    const pixelY = centerY - (y * (canvas.height / 12));

    ctx.fillStyle = status ? '#A5D6A7' : '#E57373';

    ctx.beginPath();
    ctx.arc(pixelX, pixelY, 5, 0, 2 * Math.PI);
    ctx.fill();
    ctx.closePath();
}

canvas.addEventListener("click", (event) => {
    document.querySelectorAll(".error").forEach(el => el.remove());

    const x = (event.offsetX - canvas.width / 2) / (canvas.width / 12);
    const y = (canvas.height / 2 - event.offsetY) / (canvas.height / 12);
    const sliderInstance = ice.ace.instance('frm:sliderR');
    const r = parseFloat(sliderInstance.getValue());
    if (validate(x, y, r)) {
        setTimeout(() => updatePointsFromTable(), 500);
    }
});




const handleSubmit = () => {
    const xSelector = document.getElementById("frm:xSelector");
    const yInput = document.getElementById("frm:yInput");
    const sliderInstance = ice.ace.instance('frm:sliderR');

    const xValue = parseFloat(xSelector.value);
    const yValue = parseFloat(yInput.value);
    const rValue = parseFloat(sliderInstance.getValue());

    if (validate(xValue, yValue, rValue)) {
        setTimeout(() => updatePointsFromTable(), 1000);
    }
};



const validate = (xValue, yValue, rValue) => {
    document.querySelectorAll(".error").forEach(e => e.remove());

    const hiddenX = document.getElementById("hiddenForm:xInput");
    const hiddenY = document.getElementById("hiddenForm:yInput");
    const hiddenR = document.getElementById("hiddenForm:rInput");

    console.log(`Validated values: X=${xValue}, Y=${yValue}, R=${rValue}`);
    hiddenX.value = xValue;
    hiddenY.value = yValue;
    hiddenR.value = rValue;
    document.getElementById("hiddenForm:submit-button").click();

    return true;
};


const updatePointsFromTable = () => {
    const table = document.getElementById("frm:table");
    const rows = table.querySelectorAll("tbody tr");
    const points = [];

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const x = parseFloat(cells[0].textContent.trim());
        const y = parseFloat(cells[1].textContent.trim());
        const r = parseFloat(cells[2].textContent.trim());
        const status = cells[3].textContent.trim().toLowerCase() === 'true';
        points.push({ x, y, r, status });
    });

    sessionStorage.setItem('points', JSON.stringify(points));
    console.log("Updated points from table:", points);

    const sliderInstance = ice.ace.instance('frm:sliderR');
    const currentR = parseFloat(sliderInstance.getValue());
    redrawPoints(currentR);
};
document.addEventListener('DOMContentLoaded', () => {
    updatePointsFromTable();
});
