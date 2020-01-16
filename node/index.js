var express = require('express');
var app = express();

app.get('/accounts/:id', function (req, res) {
  res.json({
    "iban": "nope",
    "cards": [
      {
        "cardNumber": "123456789",
        "pin": "1234",
      }
    ]
  });
});

app.listen(9080, function () {
  console.log('Example app listening on port 9080!');
});
