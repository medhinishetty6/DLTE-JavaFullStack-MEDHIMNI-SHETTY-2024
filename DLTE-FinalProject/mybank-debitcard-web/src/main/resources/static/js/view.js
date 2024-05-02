const request = {
    "debitCardNumber":card,
    "accountNumber":account,
    "debitCardCvv":cvv,
    "debitCardPin":pin,
    "debitCardExpiry":expiry,
    "debitCardStatus":status,
    "domesticLimit":dlimit,
    "internationalLimit":ilimit
};

$.ajax({
    url:"http://localhost:8083/debitcard/activate/3692468135796673",
    type:"PUT",
    dataType:"text",
    contentType:"application/json;charset=utf-8",
    data: `
                        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
                            <soapenv:Header/>
                            <soapenv:Body>
                                <deb:viewDebitCardRequest/>
                            </soapenv:Body>
                        </soapenv:Envelope>`,
    data:JSON.stringify(request),
    success:function(response){
        let element = $("#status")
        element.append(`<h1>Status updated successfully</h1>`)
    }
});


