(:*******************************************************:)
(: Test: K-ExpandedQNameConstructFunc-10                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `QName((), "local") eq xs:QName("local")`. :)
(:*******************************************************:)
QName((), "local") eq xs:QName("local")