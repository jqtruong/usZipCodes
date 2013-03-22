// Event listener, if bool is true:
// - show spinner
// - disable the buttons
// Else, revert changes.
// Caveat: the tag links are still clickable and cancel the recollecting of postal codes info.
function recollect(bool) {
    $('#spinner').toggle(bool);
    if (bool) {
        $('#actions input').attr('disabled', true);
    }
    else {
        $('#actions input').removeAttr('disabled');
        location.reload();
    }
}
