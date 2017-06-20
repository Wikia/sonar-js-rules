$.proxy(function() {}, this); // Noncompliant
$.proxy(this.someMethod, this); // Noncompliant

$.each(someArray, function (item, i) {});

$('body').removeClass('foo');
