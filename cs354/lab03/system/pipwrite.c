/* pipwrite.c - pipwrite */

#include <xinu.h>

/*------------------------------------------------------------------------
 * pipwrite  --  Write to a pipe, blocking if full
 *------------------------------------------------------------------------
 */
syscall	pipwrite(
		pipid32		pip,		/* ID of pipe to write to			*/
		char 		*buf,		/* pointer to buffer to write from	*/
		uint32		len			/* number of bytes to write			*/
	)
{
	intmask	mask;				/* saved interrupt mask				*/

	mask = disable();

	if (isbadpip(pip)) {
		restore(mask);
		return SYSERR;
	}

	struct pentry *pipptr;		/* ptr to pipe table entry			*/
	
	pipptr = &piptab[pip];
	if (pipptr->pstate != PIPE_CONNECTED ||
		(pipptr->pend1 != getpid() && pipptr->pend2 != getpid())) {
		restore(mask);
		return SYSERR;
	}

	/* Ensure there is enough space for the entire write request */
	if (semcount(pipptr->pwrsem) < len) {
		restore(mask);
		return SYSERR;
	}

	char *buffer = buf;			/* local copy of buffer				*/
	int count = 0;				/* character count for buffer		*/

	/* Write characters to the circular buffer */
	while (count < len)
	{
		wait(pipptr->pwrsem);
		
		pipptr->pbuf[(pipptr->pbufs + pipptr->pbufc) % PIPE_SIZ] = *buffer++;
		count++;
		pipptr->pbufc++;
	
		signal(pipptr->prdsem);
	}

	restore(mask);
	return count;
}
