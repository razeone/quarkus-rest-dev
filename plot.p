set terminal png size 1024
set output "resultados-500*500-p.png"
set title "500 peticiones, 500 peticiones concurrentes"
set size ratio 0.6
set grid y
set xlabel "peticiones"
set ylabel "tiempo de respuesta (ms)"
plot "output-500-p.txt" using 9 smooth sbezier with lines title ""
