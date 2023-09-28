<!DOCTYPE html>
<html>
<head>
  <title>Tabella con frecce di navigazione</title>
  <style>
    table {
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
    }
    .navigation {
      text-align: center;
      margin-top: 10px;
    }
  </style>
  <script>
    var currentRow = 1;

    function showData(index) {
      var table = document.getElementById("data-table");
      var rowCount = table.rows.length;

      if (index >= 0 && index < rowCount) {
        for (var i = 0; i < rowCount; i++) {
          table.rows[i].style.display = i === index ? "" : "none";
        }
        currentRow = index;
      }
    }

    function previousData() {
      showData(currentRow - 1);
    }

    function nextData() {
      showData(currentRow + 1);
    }
  </script>
</head>
<body>
  <h1>Dati da mostrare</h1>
  <table id="data-table">
    <tr>
      <th>Nome</th>
      <th>Cognome</th>
      <th>Età</th>
    </tr>
    <tr>
      <td>Mario</td>
      <td>Rossi</td>
      <td>30</td>
    </tr>
    <tr>
      <td>Luca</td>
      <td>Bianchi</td>
      <td>25</td>
    </tr>
    <tr>
      <td>Sara</td>
      <td>Verdi</td>
      <td>35</td>
    </tr>
  </table>
  <div class="navigation">
    <button onclick="previousData()">Precedente</button>
    <button onclick="nextData()">Successivo</button>
  </div>
</body>
</html>
