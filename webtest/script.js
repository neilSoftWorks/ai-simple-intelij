const table = document.getElementById('businessDetailsTable').getElementsByTagName('tbody')[0];

function fetchBusinessDetails() {
    fetch('http://localhost:8080/api/applications')
        .then(response => response.json())
        .then(data => {
            table.innerHTML = '';
            data.forEach(detail => {
                let row = table.insertRow();
                let idCell = row.insertCell(0);
                let nameCell = row.insertCell(1);
                let addressCell = row.insertCell(2);
                let contactCell = row.insertCell(3);
                let phoneCell = row.insertCell(4);
                let emailCell = row.insertCell(5);
                let industryCell = row.insertCell(6);

                idCell.textContent = detail.id;
                nameCell.textContent = detail.name;
                addressCell.textContent = detail.address;
                contactCell.textContent = detail.contact;
                phoneCell.textContent = detail.phone;
                emailCell.textContent = detail.email;
                industryCell.textContent = detail.industry;

                row.addEventListener('dblclick', function() {
                    handleRowDoubleClick(detail.id);
                });
            });
        })
        .catch(error => console.error('Error fetching business details:', error));
}

function handleRowDoubleClick(id) {
    if (confirm("Edit or Delete ID: " + id + "? (OK to Edit, Cancel to Delete)")) {
        editBusinessDetails(id);
    } else {
        deleteBusinessDetails(id);
    }
}

function editBusinessDetails(id) {
    window.location.href = `edit.html?id=${id}`;
}

function deleteBusinessDetails(id) {
    fetch(`http://localhost:8080/api/applications/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert('Business details deleted successfully.');
            fetchBusinessDetails();
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
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const industry = document.getElementById('industry').value;

    fetch('http://localhost:8080/api/applications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, address, contact, phone, email, industry })
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

fetchBusinessDetails();
