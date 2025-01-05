// Function to send the long URL to the server and generate a shortened URL
function shortenUrl() {
    const longUrl = document.getElementById("longUrl").value; // Get the user input URL

    fetch('/shorty/shorten', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ longUrl }),
    })
        .then(response => {
            // If the server returns an error.
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text || "Invalid URL"); // Throw error
                });
            }
            return response.text(); // If successful, get the shortened URL as a string
        })
        .then(shortUrl => {
            // Display the shortened URL with a message
            const result = document.getElementById("result");
            result.innerHTML = `
                <p>Shortened URL: <a href="${shortUrl}" target="_blank">${shortUrl}</a></p>
                <p><strong>Note:</strong> This URL is valid for 24 hours.</p>`;
        })
        .catch(error => {
            showPopup(error.message);
        });
}

// Function to create a temporary popup for error messages
function showPopup(message) {
    const popup = document.createElement("div");
    popup.className = "popup";
    popup.innerText = message;
    document.body.appendChild(popup);

    setTimeout(() => {
        popup.remove();
    }, 3000);
}
