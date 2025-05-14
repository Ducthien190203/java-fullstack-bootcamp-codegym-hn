function mouseover(){
    document.getElementById('demo').style.color='red';

}
function mouseout(){
    document.getElementById('demo').style.color='black';
}
var demo = document.getElementById('demo');
demo.addEventListener('mouseover',mouseover);
demo.addEventListener('mouseout',mouseout);
