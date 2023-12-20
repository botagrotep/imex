jQuery(document).ready(function () {
    let tdWithComment = $('td.comment');
    console.log(tdWithComment.html());
    tdWithComment.each(function (n) {
        this.addEventListener('click', function (e) {
            $(this).find('h6').attr('hidden', 'hidden');
            $(this).find('form').removeAttr('hidden');
        });
    });
});