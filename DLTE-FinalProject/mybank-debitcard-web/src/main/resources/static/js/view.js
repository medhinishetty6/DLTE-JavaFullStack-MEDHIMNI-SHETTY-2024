function ActiveDetails(){

    const debitCard = {
        "debitCardNumber":debitCardNumber,
        "accountNumber":accountNumber,
        "debitCardCvv":debitCardCvv,
        "debitCardPin":debitCardPin,
        "debitCardExpiry":debitCardExpiry,
        "debitCardStatus":debitCardStatus,
        "domesticLimit":domesticLimit,
        "internationalLimit":internationalLimit
    };

    console.log("Debit Card Data:", debitCard);
    $.ajax({
        url:"http://localhost:8083/debitcard/activate/"+debitCardNumber,
        type:"PUT",
        dataType:"text",
        contentType:"application/json;charset=utf-8",
        data:JSON.stringify(debitCard),
        success:function(response){
            // Update card status to "activated"
            $('#status_' + debitCardNumber).text('Status: Active');

            // Replace activate button with a paragraph message
            $('#activate_button_' + debitCardNumber).replaceWith('<p class="card-text text-success">DebitCard activated successfully</p>');
        },
        error:function(err){
            let element = $("#status")
            element.append(`<h1>${err.status}</h1>`)
        }
    });
}

function getDebitCardDetails(){
    let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
            <soapenv:Header/>
            <soapenv:Body>
                <deb:viewDebitCardRequest/>
            </soapenv:Body>
        </soapenv:Envelope>`;

    $.ajax({
        url: "http://localhost:8083/debitcardrepo",
        type: "POST",
        dataType: "xml",
        contentType: "text/xml;charset=utf-8",
        data: soapRequest,
        success: function(response) {
            // Clear existing cards

            $('#debit').empty();

            const debitCard = $(response).find('ns2\\:debitCard');

            if(debitCard.length==0){
             window.location.href = `error?code=400&message=Requested page is not available, Page Under Construction`; }

                    var exceptionRegex = /EXC00\d\s*:/;
            $(response).find('ns2\\:debitCard').each(function() {
                        if(exceptionRegex.test($(this).find('ns2\\:message').text())) {
                            var errorMessage = $(this).find('ns2\\:message').text().replace(exceptionRegex, '').trim();
                            $("#modalhead").text(`No cards available `);
                            $("#message").text(errorMessage);
                            $("#showmodal").modal("show");
                        }

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
                                    <p id="status_${debitCardNumber}" class="card-text text-light">Status: ${debitCardStatus}</p>
                                    <p class="card-text text-light">Domestic Limit: ${domesticLimit}</p>
                                    <p class="card-text text-light">International Limit: ${internationalLimit}</p>
                                    <div class="row justify-between">
                                     <button id="activate_button_${debitCardNumber}" type="button" class="btn mb-3" onclick="ActiveDetails()">Activate</button>
                                     <button type="submit" class="btn mb-3" onclick="errorPage()">Update</button>
                                     <button type="submit" class="btn mb-3" onclick="errorPage()">Block</button>
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

function errorPage(){
    const errorMessage = "Page under construction";
    window.location.href = `error?message=${encodeURIComponent(errorMessage)}`;

}


// Call getDebitCardDetails function when the page loads
$(document).ready(function() {
    getDebitCardDetails();
});
