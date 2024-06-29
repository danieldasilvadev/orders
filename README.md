# orders exercise

## arguments:
- first and second arguments are dates in the format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
- third argument and beyond are optional and are the names of the columns to be displayed in the output. If no columns are specified, all columns are displayed.
 
## commands:
- only dates:
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" `

- passing dates and intervals:
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" "1-3"`
  - `java -jar out/artifacts/orders_jar/arorders.jar "2021-01-01" "2024-07-01" "1-3" "4-7" "7-12" ">12"`
 