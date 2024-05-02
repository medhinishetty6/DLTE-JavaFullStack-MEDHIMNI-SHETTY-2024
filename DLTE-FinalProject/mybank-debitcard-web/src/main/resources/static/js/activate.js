// $(document).ready(()=>{
//
//     $("#cancel").click(()=>{
//         $("#debitCardNumber").val("")
//         $("#accountNumber").val("")
//         $("#debitCardCvv").val("")
//         $("#debitCardPin").val("")
//         $("#debitCardExpiry").val("")
//         $("#debitCardStatus").val("")
//         $("#domesticLimit").val("")
//         $("#internationalLimit").val("")
//
//     });
//
//     $("#add").click(()=>{
//         const card = $("#debitCardNumber").val()
//         const account = $("#accountNumber").val()
//         const cvv = $("#debitCardCvv").val()
//         const pin = $("#debitCardPin").val()
//         const expiry =$("#debitCardExpiry").val()
//         const status =$("#debitCardStatus").val()
//         const dlimit =$("#domesticLimit").val()
//         const ilimit =$("#internationalLimit").val()
//
//         const debitCard = {
//         accountNumber: accountNumber,
//         debitCardNumber: debitCardNumber,
//         debitCardPin: pin,
//         debitCardStatus: 'Inactive'
//     };
//
//
//         $.ajax({
//             url:"http://localhost:8083/debitcard/activate/3692468135796673",
//             type:"PUT",
//             dataType:"text",
//             contentType:"application/json;charset=utf-8",
//             data:JSON.stringify(request),
//             success:function(response){
//                 let element = $("#status")
//                 element.append(`<h1>Status updated successfully</h1>`)
//             },
//             error:function(err){
//                 let element = $("#status")
//                 element.append(`<h1>${err.status}</h1>`)
//             }
//         });
//     });
// });