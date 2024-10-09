SELECT DISTINCT c.nombre, c.apellidos,c.ciudad 
FROM cliente AS c
JOIN inscripcion AS i ON c.id = i.idCliente
JOIN producto AS p ON i.idProducto= p.id
JOIN disponibilidad AS d ON p.idProducto = d.idProducto
JOIN sucursal AS s ON d.idSucursal = s.id
JOIN visitan AS v ON c.id = v.idCliente AND s.id = v.idSucursal
WHERE NOT EXISTS (
    SELECT 1
    FROM disponibilidad AS d2
    WHERE d2.idProducto = p.id
      AND d2.idSucursal NOT IN (
          SELECT s2.id
          FROM sucursal s2
          JOIN disponibilidad AS d3 ON s2.id = d3.idSucursal
          WHERE d3.idProducto = p.id
      )
);

