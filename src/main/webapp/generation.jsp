
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String [] primeNumbers = (String[]) request.getAttribute("arrayOfPrimeNumbers");
    StringBuilder sbForPrimeNumbers = new StringBuilder();
    for(int i=0;i<primeNumbers .length;i++)
        sbForPrimeNumbers.append(primeNumbers [i]+",");

    String [] spacingValues = (String[]) request.getAttribute("arrayOfSpacingValues");
    StringBuilder sbForSpacingValues = new StringBuilder();
    for(int i=0;i<spacingValues .length;i++)
        sbForSpacingValues.append(spacingValues [i]+",");
%>

<html>
<head>
    <title>Generation</title>
</head>
<body onload="addTextBox()">
<div id="divsection"></div>
<br>
<form action="<%=request.getContextPath() %>/sum" method="POST">
    <button id ="button" >See summary</button>
</form>
</body>
</html>

<script>
    function addTextBox() {

        temp="<%=sbForPrimeNumbers.toString()%>";
        var jsArrayOfPrimeNumbers = new Array();
        jsArrayOfPrimeNumbers = temp.split(',','<%=primeNumbers .length%>');

        temp="<%=sbForSpacingValues.toString()%>";
        var jsArrayOfSpacingValues = new Array();
        jsArrayOfSpacingValues  = temp.split(',','<%=spacingValues .length%>');

        var highestValue = ${theHighestSpacingValue};
        var amount = ${quantity};

            for (var i = 0; i <amount; i++) {

                var button = document.createElement('button'); // Create button
                button.style.width = '40px';
                button.style.height = '20px';
                button.type = "text";
                button.id = "input" + i;
                button.textContent = jsArrayOfPrimeNumbers[i];
                button.style.backgroundColor='#aacc9f';
                document.getElementById('divsection').appendChild(button); //Append it

                if (i<(amount-1)){

                    var button1 = document.createElement('button'); // Create button
                    button1.style.width = '40px';
                    button1.style.height = '20px';
                    button1.type = "text";
                    button1.id = "input" + i;
                    button1.textContent = jsArrayOfSpacingValues[i];

                    if (jsArrayOfSpacingValues[i] == highestValue) {
                        button1.style.backgroundColor = '#ff9e81';
                    }
                    else {
                        button1.style.backgroundColor = '#e4e4e4';
                    }
                    document.getElementById('divsection').appendChild(button1); //Append it
                }
            }
    };

</script>