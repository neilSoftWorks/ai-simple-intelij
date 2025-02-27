const businessDetailsList = document.getElementById('businessDetailsList');
const businessDetailsForm = document.getElementById('businessDetailsForm');

function loadBusinessDetails() {
    fetch('http://localhost:8080/api/applications')
        .then(response => response.json())
        .then(data => {
            businessDetailsList.innerHTML = '';
            data.forEach(business => {
                const div = document.createElement('div');
                div.innerHTML = `
                    <h2>${business.name}</h2>
                    <p>Contact: ${business.contactDetails}</p>
                    <p>Address: ${business.address}</p>
                    <p>Industry: ${business.industry}</p>
                    <p>Financial Info: ${business.financialInformation}</p>
                    <p>Phone Number: ${business.phoneNumber}</p>
                    <p>Email Address: ${business.emailAddress}</p>
                    <button onclick="editBusinessDetails(${business.id})">Edit</button>
                    <button onclick="deleteBusinessDetails(${business.id})">Delete</button>
                    <hr>
                `;
                businessDetailsList.appendChild(div);
            });
        })
        .catch(error => console.error('Error loading business details:', error));
}

function createBusinessDetails(businessDetails) {
    fetch('http://localhost:8080/api/applications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(businessDetails)
    })
    .then(response => response.json())
    .then(data => {
        loadBusinessDetails();
        businessDetailsForm.reset();
    })
    .catch(error => console.error('Error creating business details:', error));
}

function updateBusinessDetails(id, businessDetails) {
    fetch(`http://localhost:8080/api/applications/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(businessDetails)
    })
    .then(response => response.json())
    .then(data => loadBusinessDetails())
    .catch(error => console.error('Error updating business details:', error));
}

function deleteBusinessDetails(id) {
    fetch(`http://localhost:8080/api/applications/${id}`, {
        method: 'DELETE'
    })
    .then(() => loadBusinessDetails())
    .catch(error => console.error('Error deleting business details:', error));
}

function editBusinessDetails(id) {
    fetch(`http://localhost:8080/api/applications/${id}`)
        .then(response => response.json())
        .then(business => {
            document.getElementById('id').value = business.id;
            document.getElementById('name').value = business.name;
            document.getElementById('contactDetails').value = business.contactDetails;
            document.getElementById('address').value = business.address;
            document.getElementById('industry').value = business.industry;
            document.getElementById('financialInformation').value = business.financialInformation;
            document.getElementById('phoneNumber').value = business.phoneNumber;
            document.getElementById('emailAddress').value = business.emailAddress;
        })
        .catch(error => console.error('Error editing business details:', error));
}

businessDetailsForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const id = document.getElementById('id').value;
    const name = document.getElementById('name').value;
    const contactDetails = document.getElementById('contactDetails').value;
    const address = document.getElementById('address').value;
    const industry = document.getElementById('industry').value;
    const financialInformation = document.getElementById('financialInformation').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const emailAddress = document.getElementById('emailAddress').value;

    // Email validation
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    if (!emailRegex.test(emailAddress)) {
        alert("Please enter a valid email address.");
        return;
    }

    const businessDetails = {
        name: name,
        contactDetails: contactDetails,
        address: address,
        industry: industry,
        financialInformation: financialInformation,
        phoneNumber: phoneNumber,
        emailAddress: emailAddress
    };

    if (id) {
        updateBusinessDetails(id, businessDetails);
    } else {
        createBusinessDetails(businessDetails);
    }
});

window.onload = loadBusinessDetails;
