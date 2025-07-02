function renderCalendar() {
    const calendar = document.getElementById("calendar");
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();

    const monthNames = ["January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"];
    const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = new Date(year, month + 1, 0).getDate();

    let html = `<div class="calendar-header">${monthNames[month]} ${year}</div>`;
    html += '<div class="calendar-grid">';

    for (let d of days) {
      html += `<div style="font-weight:bold">${d}</div>`;
    }

    for (let i = 0; i < firstDay; i++) {
      html += `<div></div>`;
    }

    for (let day = 1; day <= lastDate; day++) {
      const isToday = day === today.getDate();
      html += `<div class="${isToday ? 'today' : ''}">${day}</div>`;
    }

    html += '</div>';
    calendar.innerHTML = html;
  }

  renderCalendar();