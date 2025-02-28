function submitApplication() {
    const businessName = document.getElementById('businessName').value;
    const ownerName = document.getElementById('ownerName').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    fetch('/api/applications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            businessName: businessName,
            ownerName: ownerName,
            phoneNumber: phoneNumber,
        }),
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('results').innerText = JSON.stringify(data);
    });
}

function fetchApplications() {
    fetch('/api/applications')
    .then(response => response.json())
    .then(data => {
        document.getElementById('results').innerText = JSON.stringify(data);
    });
}

fetchApplications();
