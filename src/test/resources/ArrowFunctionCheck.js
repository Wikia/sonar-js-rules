[].forEach(foo => {}); // Noncompliant
var blabla = () => {}; // Noncompliant

function foo() {
	console.log('bar');

	[].forEach(function(a) {});
}
