const table = document.getElementById('businessDetailsTable').getElementsByTagName('tbody')[0];

function fetchBusinessDetails() {
    fetch('/api/applications')
        .then(response => response.json())
        .then(data => {
            table.innerHTML = ''; // Clear existing rows
            data.forEach(detail => {
                let row = table.insertRow();
                let idCell = row.insertCell(0);
                let nameCell = row.insertCell(1);
                let addressCell = row.insertCell(2);
                let contactCell = row.insertCell(3);

                idCell.textContent = detail.id;
                nameCell.textContent = detail.name;
                addressCell.textContent = detail.address;
                contactCell.textContent = detail.contact;

                row.addEventListener('dblclick', function() {
                    // Handle double-click (edit or delete)
                    handleRowDoubleClick(detail.id);
                });
            });
        })
        .catch(error => console.error('Error fetching business details:', error));
}

function handleRowDoubleClick(id) {
    // Implement edit or delete logic here
    if (confirm("Edit or Delete ID: " + id + "? (OK to Edit, Cancel to Delete)")) {
        // Edit logic
        editBusinessDetails(id);
    } else {
        // Delete logic
        deleteBusinessDetails(id);
    }
}

function editBusinessDetails(id) {
    // Redirect to a form or show a modal for editing
    window.location.href = `edit.html?id=${id}`;
}

function deleteBusinessDetails(id) {
    fetch(`/api/applications/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert('Business details deleted successfully.');
            fetchBusinessDetails(); // Refresh the table
        } else {
            alert('Failed to delete business details.');
        }
    })
    .catch(error => console.error('Error deleting business details:', error));
}

function submitBusinessDetails() {
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const contact = document.getElementById('contact').value;

    fetch('/api/applications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, address, contact })
    })
    .then(response => {
        if (response.ok) {
            alert('Business details submitted successfully.');
            fetchBusinessDetails();
        } else {
            alert('Failed to submit business details.');
        }
    })
    .catch(error => console.error('Error submitting business details:', error));
}

// Fetch and display business details on page load
fetchBusinessDetails();
