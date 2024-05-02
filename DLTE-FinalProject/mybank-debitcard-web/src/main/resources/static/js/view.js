
function getDebitCardDetails(){
    let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
            <soapenv:Header/>
            <soapenv:Body>
                <deb:viewDebitCardRequest/>
            </soapenv:Body>
        </soapenv:Envelope>`;

    $.ajax({
        url: "http://localhost:8083/debitcardrepo/debitcard.wsdl",
        type: "POST",
        dataType: "xml",
        contentType: "text/xml;charset=utf-8",
        data: soapRequest,
        success: function(response) {
            // Clear existing cards
            $('#debit').empty();

            // Parse XML response
            $(response).find('ns2\\:debitCard').each(function() {
                // Extract debit card details
                window.debitCardNumber = $(this).find('ns2\\:debitCardNumber').text();
                window.accountNumber = $(this).find('ns2\\:accountNumber').text();

                window.debitCardCvv = $(this).find('ns2\\:debitCardCvv').text();
                window.debitCardPin = $(this).find('ns2\\:debitCardPin').text();
                window.debitCardExpiry = $(this).find('ns2\\:debitCardExpiry').text();
                window.debitCardStatus = $(this).find('ns2\\:debitCardStatus').text();
                window.domesticLimit = $(this).find('ns2\\:domesticLimit').text();
                window.internationalLimit = $(this).find('ns2\\:internationalLimit').text();

                // Create card HTML
                const cardHtml = `
                        <div class="col-md-4 mb-4">
                            <div class="card" style="background-color:#182057;">
                                <div class="card-body">
                                    <p class="card-text text-light">DebitCard Number: ${debitCardNumber}</p>
                                    <p class="card-text text-light">Account Number: ${accountNumber}</p>
                                    <p class="card-text text-light">Card Cvv: ${debitCardCvv}</p>
                                    <p class="card-text text-light">Expiry: ${debitCardExpiry}</p>
                                    <p class="card-text text-light">Status: ${debitCardStatus}</p>
                                    <p class="card-text text-light">Domestic Limit: ${domesticLimit}</p>
                                    <p class="card-text text-light">International Limit: ${internationalLimit}</p>
                                    <div class="row justify-between">
                                     <button type="submit" id="update" onclick="ActiveDetails()" class="btn mb-3"><a href="/card/activate/${debitCardNumber}">Activate</a></button>
                                     <button type="submit" class="btn mb-3">Update</button>
                                     <button type="submit"  class="btn mb-3">Block</button>
                                    </div>
                                </div>
                            </div>
                        </div>`;

                // Append card to debit
                $('#debit').append(cardHtml);
            });
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}


// Call getDebitCardDetails function when the page loads
$(document).ready(function() {
    getDebitCardDetails();
});


